package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.ExerciseRecordDTO;
import com.changyoong.ounmo.service.ExerciseRecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ounmo/record")
public class ExerciseRecordController {
    private final ExerciseRecordService exerciseRecordService;

    @GetMapping("/all")
    public List<ExerciseRecordDTO> findAll() {
        return exerciseRecordService.findAll();
    }

    @PostMapping("/save")
    public @ResponseBody Long saveRecord(@RequestBody ExerciseRecordDTO exerciseRecordDTO) {
         return exerciseRecordService.saveRecord(exerciseRecordDTO);
    }
}
