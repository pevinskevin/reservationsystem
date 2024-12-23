package com.pevinskevin.reservationsystem.Repositories;

import com.pevinskevin.reservationsystem.Models.AssignedTableHelper;
import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Models.CafeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public class ReservationRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int checkTotalSeatCapacity() {
        String query = "SELECT SUM(seat_capacity)" +
                "AS total_seat_capacity FROM cafe_table";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public int checkForAssignedTables(Reservation reservation) {
        String query = "SELECT SUM(cafe_table.seat_capacity) " +
                "FROM cafe_table_reservation " +
                "JOIN cafe_table ON cafe_table_reservation.cafe_table_id = cafe_table.id " +
                "JOIN reservation ON cafe_table_reservation.reservation_id = reservation.id " +
                "WHERE reservation.reservation_date = ? " +
                "AND NOT (? > ADDTIME(reservation.time, SEC_TO_TIME(reservation.duration_in_hours*3599)) " +
                "OR ADDTIME(?, SEC_TO_TIME(?*3600)) < reservation.time)";
        Integer totalSeatsReserved = jdbcTemplate.queryForObject(query, new Object[] {reservation.getReservationDate(), reservation.getTime(), reservation.getTime(), reservation.getDurationInHours()}, Integer.class);

        if (totalSeatsReserved == null) {
            return 0;
        }
        return totalSeatsReserved;
    }

    public int checkUnavailableSeats(Reservation reservation) {
        String query = "SELECT SUM(number_of_seats) " +
                "FROM reservation WHERE reservation_date = ? " +
                "AND NOT(? > ADDTIME(time, SEC_TO_TIME(duration_in_hours*3599)) " +
                "OR ADDTIME(?, SEC_TO_TIME(?*3600)) < time)";

        Integer totalSeatsReserved = jdbcTemplate.queryForObject(query, new Object[] {reservation.getReservationDate(), reservation.getTime(), reservation.getTime(), reservation.getDurationInHours()}, Integer.class);

        if (totalSeatsReserved == null) {
            return 0;
        }

        return totalSeatsReserved;
    }

    public Reservation getReservationByUrl(String Url) {
        String query = "SELECT * FROM Reservation WHERE url = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{Url}, new BeanPropertyRowMapper<>(Reservation.class));
    }

    public String addReservation(Reservation reservation) {
        String url = UUID.randomUUID().toString();
        String query = "INSERT INTO Reservation" +
                " (name, email, company_name, phone_number, number_of_seats, reservation_date, time, duration_in_hours, celebration, birthday, comments, url)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, reservation.getName(), reservation.getEmail(), reservation.getCompanyName(), reservation.getPhoneNumber(), reservation.getNumberOfSeats(), reservation.getReservationDate(), reservation.getTime(), reservation.getDurationInHours(), reservation.getCelebration(), reservation.getBirthday(), reservation.getComments(), url);
        return url;
    }

    public int getReservationIdUsingReservationUrl(String url){
        String query = "SELECT id FROM reservation WHERE url = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{url}, Integer.class);
    }

    public List<Integer> getReservationIdsNeedingTables() {
        String query = "SELECT reservation.id, reservation.number_of_seats " +
                "FROM reservation " +
                "LEFT JOIN cafe_table_reservation ON reservation.id = cafe_table_reservation.reservation_id " +
                "LEFT JOIN cafe_table ON cafe_table_reservation.cafe_table_id = cafe_table.id " +
                "GROUP BY reservation.id " +
                "HAVING SUM(cafe_table.seat_capacity) IS NULL OR SUM(cafe_table.seat_capacity) < reservation.number_of_seats";

        RowMapper<Integer> rowMapper = (rs, rowNum) -> rs.getInt("id");
        return jdbcTemplate.query(query, rowMapper);
    }

    public List<CafeTable> getAvailableTables(LocalDate desiredDate, LocalTime desiredStartTime, LocalTime desiredEndTime) {
        String sql = "SELECT * " +
                "FROM cafe_table ct " +
                "WHERE ct.id NOT IN (" +
                "    SELECT ctr.cafe_table_id " +
                "    FROM cafe_table_reservation ctr " +
                "    JOIN reservation r ON ctr.reservation_id = r.id " +
                "    WHERE r.reservation_date = ? " +
                "      AND ((r.time <= ? AND ADDTIME(r.time, SEC_TO_TIME(r.duration_in_hours * 3600)) > ?) OR " +
                "           (r.time < ? AND ADDTIME(r.time, SEC_TO_TIME(r.duration_in_hours * 3600)) >= ?) OR " +
                "           (r.time >= ? AND ADDTIME(r.time, SEC_TO_TIME(r.duration_in_hours * 3600)) <= ?))" +
                ")";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CafeTable.class), desiredDate, desiredStartTime,
                desiredStartTime, desiredEndTime, desiredEndTime, desiredStartTime, desiredEndTime);
    }

    public void assignTableToReservation(int reservationId, int tableId) {
        String query = "INSERT INTO cafe_table_reservation (reservation_id, cafe_table_id) values (?, ?)";
        jdbcTemplate.update(query, reservationId, tableId);

    }

    public List<Integer> getListOfAssignedTablesWithIdList(List<Integer> idList) {
        String query = "SELECT SUM(cafe_table.seat_capacity) " +
                "FROM cafe_table_reservation " +
                "LEFT JOIN cafe_table ON cafe_table_reservation.cafe_table_id = cafe_table.id " +
                "LEFT JOIN reservation ON cafe_table_reservation.reservation_id = reservation.id " +
                "WHERE reservation.id = ? " +
                "ORDER BY reservation.reservation_date DESC";
        List<Integer> resultList = new ArrayList<>();

        for(Integer id : idList) {
            Integer result = jdbcTemplate.queryForObject(query, Integer.class, id);
            if (result != null) {
                resultList.add(result);
            } else {
                resultList.add(0);
            }
        }
        return resultList;
    }

    public void deleteBookingUsingBookingUrl(String reservationUrl){
        String query = "DELETE FROM reservation WHERE url = ?";
        jdbcTemplate.update(query, reservationUrl);
    }

    public void updateReservation(Reservation reservation, String reservationUrl){
        String query = "UPDATE reservation SET name = ?, email = ?, company_name = ?, phone_number = ?, number_of_seats = ?, reservation_date = ?, time = ?, duration_in_hours = ?, celebration = ?, birthday = ?, comments = ?, url = ? WHERE url = ?";
        jdbcTemplate.update(query, reservation.getName(), reservation.getEmail(), reservation.getCompanyName(), reservation.getPhoneNumber(), reservation.getNumberOfSeats(), reservation.getReservationDate(), reservation.getTime(), reservation.getDurationInHours(), reservation.getCelebration(), reservation.getBirthday(), reservation.getComments(), reservationUrl, reservationUrl);
    }

    public List<Reservation> getAllUpcomingReservations() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String query = "SELECT * FROM reservation WHERE reservation_date >= ?";
        return jdbcTemplate.query(query, new Object[]{today}, new BeanPropertyRowMapper<>(Reservation.class));
    }

    // In ReservationRepository.java
    public List<AssignedTableHelper> getAssignedTablesByReservationId(int reservationId) {
        String query = "SELECT r.id AS reservation_id, ct.table_number, ct.seat_capacity " +
                "FROM cafe_table_reservation ctr " +
                "JOIN reservation r ON ctr.reservation_id = r.id " +
                "JOIN cafe_table ct ON ctr.cafe_table_id = ct.id " +
                "WHERE r.id = ?";
        return jdbcTemplate.query(query, new Object[]{reservationId}, (rs, rowNum) ->
                new AssignedTableHelper(
                        rs.getInt("reservation_id"),
                        rs.getInt("table_number"),
                        rs.getInt("seat_capacity")
                )
        );
    }
}