package com.mustache.bbs.controller;

import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.domain.entity.Comment;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/hospitals")
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping(value = "")
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model, @PageableDefault(size = 20, sort = "id",direction = Sort.Direction.ASC)
    Pageable pageable) {
        log.info("keyword : {} ", keyword);
        //Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        Page<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospitals/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);

        if (!optHospital.isEmpty()) {
            model.addAttribute("hospital", optHospital.get());
            return "hospitals/show";
        } else{
            model.addAttribute("message", String.format("%d 이 없어요.", id));
            return "hospitals/error";
        }

    }
}
