package com.jimmy.flightticket3.repository;

import com.jimmy.flightticket3.entity.FlightEntity;
import com.jimmy.flightticket3.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<FlightEntity, Integer> {

    /**
     * 根据多个可选参数查找航班。
     * 注意：对于复杂的组合查询，通常推荐使用 Specification, QueryDsl 或 @Query。
     * 但是，Spring Data JPA 仍然可以处理一些简单的组合 AND/OR。
     * @param flightNumber 航班号 (可空)
     * @param origin 出发地 (可空)
     * @param destination 目的地 (可空)
     * @param departureDateTime 仅考虑日期部分的出发日期 (可空)
     * @param arrivalDateTime 仅考虑日期部分的到达日期 (可空)
     * @return 匹配的航班列表
     */
    // 由于业务逻辑中需要根据日期（而不是时间点）进行范围查询，
    // 这里展示一个使用 JPQL 自定义查询的例子，因为它更灵活。
    // 如果只需要精确到天的查询，可以直接在方法名中写，例如 findByDepartureDateTimeBetween(OffsetDateTime start, OffsetDateTime end)
    @Query("SELECT f FROM FlightEntity f WHERE " +
            "(:flightNumber IS NULL OR f.flightNumber = :flightNumber) AND " +
            "(:origin IS NULL OR f.origin = :origin) AND " +
            "(:destination IS NULL OR f.destination = :destination) AND " +
            "(:departureDateTime IS NULL OR FUNCTION('DATE_TRUNC', 'day', f.departureDateTime) = :departureDateTime) AND " +
            "(:arrivalDateTime IS NULL OR FUNCTION('DATE_TRUNC', 'day', f.arrivalDateTime) = :arrivalDateTime)" +
            "ORDER BY f.departureDateTime ASC")
    List<FlightEntity> findFlightsByParameters(@Param("flightNumber") String flightNumber,
                                               @Param("origin") String origin,
                                               @Param("destination") String destination,
                                               @Param("departureDateTime") OffsetDateTime departureDateTime,
                                               @Param("arrivalDateTime") OffsetDateTime arrivalDateTime);

    @Query("SELECT f FROM FlightEntity f WHERE :flightNumber IS NULL OR f.flightNumber = :flightNumber")
    List<FlightEntity> findFlightsByFlightNumber(@Param("flightNumber") String flightNumber);
}
