package com.example.sms.validation;

import com.example.sms.entity.Subject;

/** Marker interface for Subject validation in the school management system. This
 * interface can be implemented by classes that perform validation logic for
 * Subject-related operations, such as creating, updating, or deleting a Subject.
 * It serves as a way to group and identify validation classes related to Subjects. */
public interface SubjectValidation {
    
    /** Method to validate the creation of a new Subject.
     *  This method can be implemented to check for required fields, validate
     *  data formats, and ensure that the Subject being created meets the
     *  necessary criteria. */
    Subject findById(String id);
    
    /** Method to validate the deletion of a Subject.
     *  This method can be implemented to check if the Subject exists, ensure
     *  that it is not associated with any other entities that would prevent
     *  deletion, and perform any necessary cleanup before the
     *  Subject is deleted. */
    void delete(Subject subject);
}
