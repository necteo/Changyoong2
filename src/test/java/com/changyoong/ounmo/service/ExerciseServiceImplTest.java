package com.changyoong.ounmo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ExerciseServiceImplTest {
    @Autowired
    ExerciseService exerciseService;

    @Test
    void createExercise() {

    }
}