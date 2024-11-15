package com.poc.byondmap.repository;

import com.poc.byondmap.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the "otp" table.
 * This interface extends {@link JpaRepository} to provide basic database operations
 * for the {@link Otp} entity.
 */
@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {

    /**
     * Finds an OTP record by the phone number.
     *
     * @param phoneNumber The phone number for which to find the OTP record.
     * @return The {@link Otp} record associated with the provided phone number.
     */
    Otp findByPhoneNumber(String phoneNumber);
}
