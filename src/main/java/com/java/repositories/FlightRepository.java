package com.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.java.components.BookedFlight;
import com.java.components.FlightTemplate;
import com.java.components.User;
import com.java.exception.GeneralException;
import com.java.utils.MyDataSource;
import com.java.utils.RepositorySupport;

public class FlightRepository {

	private static FlightRepository rep = null;
	private static Object bookingLock = new Object();
	private static Object schedLock = new Object();
	private static BasicDataSource ds;
	private static String errorMsg = "";

	static {
		try {
			ds = MyDataSource.getDataSource();
		} catch (GeneralException e) {
			errorMsg = "Loading Data Source failed: " + e.getMessage();
		}
	}

	private FlightRepository() {
		super();
	}

	public static FlightRepository getFlightRepository() {
		if (rep == null) {
			synchronized (FlightRepository.class) {
				if (rep == null)
					rep = new FlightRepository();
			}
		}
		return rep;
	}

	public FlightTemplate getFlightById(int id) throws GeneralException {

		FlightTemplate flight = null;
		String fetchFlightSQL = "select * from scheduled_flights where id=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchFlightSt = conn.prepareStatement(fetchFlightSQL);) {

			fetchFlightSt.setInt(1, id);
			ResultSet set = fetchFlightSt.executeQuery();

			List<FlightTemplate> ulist = RepositorySupport.mapToScheduled(set);
			if(ulist.size() > 0)
				flight = ulist.get(0);
			
			set.close();
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve flight: " + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			throw new GeneralException("No flight retrieved: " + e.getMessage());
		}

		return flight;
	}

	public List<FlightTemplate> getAllFlightsBetween(String departure, String arrival, LocalDate date)
			throws GeneralException {

		List<FlightTemplate> flights = null;

		String fetchFlightsSQL = "select * from scheduled_flights where source=? and destination=? and departure_date=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchFlightsSt = conn.prepareStatement(fetchFlightsSQL);) {

			fetchFlightsSt.setString(1, departure);
			fetchFlightsSt.setString(2, arrival);
			fetchFlightsSt.setDate(3, java.sql.Date.valueOf(date));

			ResultSet set = fetchFlightsSt.executeQuery();
			
			flights = RepositorySupport.mapToScheduled(set);

			set.close();
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve flights: " + e.getMessage());
		}

		return flights;
	}

	public List<FlightTemplate> getAllFlightsToday() throws GeneralException {

		List<FlightTemplate> flights = null;

		String fetchFlightsSQL = "select * from scheduled_flights where departure_date=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchFlightsSt = conn.prepareStatement(fetchFlightsSQL);) {

			fetchFlightsSt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));

			ResultSet set = fetchFlightsSt.executeQuery();

			flights = RepositorySupport.mapToScheduled(set);

			set.close();
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve flights: " + e.getMessage());
		}

		return flights;
	}

	public void addBooking(BookedFlight flight) throws GeneralException {

		String insertBookingSQL = "insert into booked_flights (airline, passenger_name, source, destination, departure_time, arrival_time, userId, flightId)\n"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		String getAvSeatsSQL = "select available_seats from scheduled_flights where id=?";
		String updateSeatsSQL = "update scheduled_flights set available_seats=? where id=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement insertBookingSt = conn.prepareStatement(insertBookingSQL);
				PreparedStatement getAvSeatsSt = conn.prepareStatement(getAvSeatsSQL);
				PreparedStatement updateSeatsSt = conn.prepareStatement(updateSeatsSQL);) {

			getAvSeatsSt.setInt(1, flight.getFlightId());
			updateSeatsSt.setInt(2, flight.getFlightId());

			insertBookingSt.setString(1, flight.getAirline());
			insertBookingSt.setString(2, flight.getPassengerName());
			insertBookingSt.setString(3, flight.getFrom());
			insertBookingSt.setString(4, flight.getTo());
			insertBookingSt.setDate(5, java.sql.Date.valueOf(flight.getDepartureTime()));
			insertBookingSt.setDate(6, java.sql.Date.valueOf(flight.getArrivalTime()));
			insertBookingSt.setInt(7, flight.getUserId());
			insertBookingSt.setInt(8, flight.getFlightId());

			// check flight available...
			int avSeat = -1;
			synchronized (bookingLock) {
				ResultSet executeQuery = getAvSeatsSt.executeQuery();
				while (executeQuery.next()) {
					avSeat = executeQuery.getInt("available_seats");
					if (avSeat > 0) {
						insertBookingSt.executeUpdate();
						updateSeatsSt.setInt(1, avSeat - 1);
						updateSeatsSt.executeUpdate();
						conn.commit();
					}
				}
			}

		} catch (SQLException e) {
			throw new GeneralException("Unable to Add booking to db : " + e.getMessage());
		}

	}

	public void deleteBooking(int bookingId) throws GeneralException {

		String deleteFlightSQL = "delete from booked_flights where id=?";
		String getAvSeatsSQL = "select available_seats from scheduled_flights where id=?";
		String updateSeatsSQL = "update scheduled_flights set available_seats=? where id=?";
		String getbooksSQL = "select flightId from booked_flights where id=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement deleteFlightSt = conn.prepareStatement(deleteFlightSQL);
				PreparedStatement getAvSeatsSt = conn.prepareStatement(getAvSeatsSQL);
				PreparedStatement getbooksSt = conn.prepareStatement(getbooksSQL);
				PreparedStatement updateSeatsSt = conn.prepareStatement(updateSeatsSQL);) {

			getbooksSt.setInt(1, bookingId);
			deleteFlightSt.setInt(1, bookingId);

			ResultSet set = getbooksSt.executeQuery();
			while (set.next()) {
				getAvSeatsSt.setInt(1, set.getInt("flightId"));
				updateSeatsSt.setInt(2, set.getInt("flightId"));
				ResultSet set1 = getAvSeatsSt.executeQuery();
				while (set1.next()) {
					updateSeatsSt.setInt(1, set1.getInt("available_seats") + 1);
				}
			}

			synchronized (bookingLock) {
				deleteFlightSt.executeUpdate();
				updateSeatsSt.executeUpdate();
				conn.commit();
			}

		} catch (SQLException e) {
			throw new GeneralException("Unable to delete flight from db : " + e.getMessage());
		}
	}

	public void addFlight(FlightTemplate flight) throws GeneralException {

		String insertUserSQL = "insert into scheduled_flights (airline, source, destination, departure_date, arrival_date, cost, seats, available_seats)\n"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ds.getConnection();
				PreparedStatement insertUserSt = conn.prepareStatement(insertUserSQL);) {
			insertUserSt.setString(1, flight.getAirline());
			insertUserSt.setString(2, flight.getFrom());
			insertUserSt.setString(3, flight.getTo());
			insertUserSt.setDate(4, java.sql.Date.valueOf(flight.getDepartureTime()));
			insertUserSt.setDate(5, java.sql.Date.valueOf(flight.getArrivalTime()));
			insertUserSt.setFloat(6, flight.getPrice());
			insertUserSt.setInt(7, flight.getSeats());
			insertUserSt.setInt(8, flight.getSeats());

			synchronized (schedLock) {
				insertUserSt.executeUpdate();
				conn.commit();
			}
			System.out.println(flight);
			System.out.println(insertUserSt);

		} catch (SQLException e) {
			throw new GeneralException("Unable to Add flight to db : " + e.getMessage());
		}
	}

	public void updateFlight(FlightTemplate flight) throws GeneralException {

		String updateFlightSQL = "update scheduled_flights set airline=?, source=?, destination=?, "
				+ "departure_date=?, arrival_date=?, cost=?, seats=?, available_seats=? where id=?";
		String checkSeatsSQL = "select available_seats, seats from scheduled_flights where id=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement updateFlightSt = conn.prepareStatement(updateFlightSQL);
				PreparedStatement checkSeatsSt = conn.prepareStatement(checkSeatsSQL);) {

			int takenSeats = -1;
			int seats = flight.getSeats();

			checkSeatsSt.setInt(1, flight.getId());
			ResultSet set = checkSeatsSt.executeQuery();

			while (set.next()) {
				int a = set.getInt("seats"), b = set.getInt("available_seats");
				takenSeats = a - b;
				if (b < 0)
					takenSeats = a;
				if (b > a)
					takenSeats = 0;
				if (takenSeats > seats)
					takenSeats = seats;
			}

			updateFlightSt.setString(1, flight.getAirline());
			updateFlightSt.setString(2, flight.getFrom());
			updateFlightSt.setString(3, flight.getTo());
			updateFlightSt.setDate(4, java.sql.Date.valueOf(flight.getDepartureTime()));
			updateFlightSt.setDate(5, java.sql.Date.valueOf(flight.getArrivalTime()));
			updateFlightSt.setFloat(6, flight.getPrice());
			updateFlightSt.setInt(7, flight.getSeats());
			updateFlightSt.setInt(8, seats - takenSeats);
			updateFlightSt.setInt(9, flight.getId());

			synchronized (schedLock) {
				updateFlightSt.executeUpdate();
				conn.commit();
			}

		} catch (SQLException e) {
			throw new GeneralException("Unable to Update flight in db : " + e.getMessage());
		}
	}

	public void deleteFlight(int flightId) throws GeneralException {
		String deleteFlightSQL = "delete from scheduled_flights where id=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement deleteFlightSt = conn.prepareStatement(deleteFlightSQL);) {

			deleteFlightSt.setInt(1, flightId);

			synchronized (schedLock) {
				deleteFlightSt.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			throw new GeneralException("Unable to delete flight from db : " + e.getMessage());
		}
	}

	// used by customer to view booking
	public BookedFlight getBooking(int id) throws GeneralException {

		BookedFlight flight = null;
		String fetchBookSQL = "select * from booked_flights where id=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchBookSt = conn.prepareStatement(fetchBookSQL);) {

			fetchBookSt.setInt(1, id);
			ResultSet set = fetchBookSt.executeQuery();


			List<BookedFlight> ulist = RepositorySupport.mapToBooked(set);
			
			if(ulist.size() > 0)
				flight = ulist.get(0);

			set.close();
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve booking: " + e.getMessage());
		}

		return flight;
	}

	// to be used by Admin
	public List<FlightTemplate> getAllFlights() throws GeneralException {

		List<FlightTemplate> flights = null;

		String fetchFlightsSQL = "select * from scheduled_flights";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchFlightsSt = conn.prepareStatement(fetchFlightsSQL);) {

			ResultSet set = fetchFlightsSt.executeQuery();

			flights = RepositorySupport.mapToScheduled(set);
			System.out.println(flights);

			set.close();
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve flights: " + e.getMessage());
		}

		return flights;
	}

	// to be used by Admin
	public List<BookedFlight> getAllBookings() throws GeneralException {

		List<BookedFlight> flights = null;
		String fetchBookSQL = "select * from booked_flights";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchBookSt = conn.prepareStatement(fetchBookSQL);) {

			ResultSet set = fetchBookSt.executeQuery();

			flights = RepositorySupport.mapToBooked(set);

			set.close();
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve bookings: " + e.getMessage());
		}

		return flights;
	}

	public List<BookedFlight> getUserHistory(User user) throws GeneralException {

		List<BookedFlight> flights = null;
		String fetchBookSQL = "select * from booked_flights where userId=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchBookSt = conn.prepareStatement(fetchBookSQL);) {

			if (user != null) {
				fetchBookSt.setInt(1, user.getId());
				ResultSet set = fetchBookSt.executeQuery();

				flights = RepositorySupport.mapToBooked(set);

				set.close();
			}
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve booking history: " + e.getMessage());
		}

		return flights;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
