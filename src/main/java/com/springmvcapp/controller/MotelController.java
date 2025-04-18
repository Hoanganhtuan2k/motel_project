package com.springmvcapp.controller;

import com.springmvcapp.model.BookModel;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.service.MotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/motels")
@RequiredArgsConstructor
public class MotelController {

    public static final String USER_LOGIN = "userLogin";
    private final MotelService motelService;

//    @GetMapping
//    public String showMotels(Model model,
//                             @AuthenticationPrincipal UserDetails userDetails) {
//        // Return the name of the view (e.g., motels.html)
//        String username = userDetails.getUsername();
//
//        model.addAttribute(USER_LOGIN, username);
//
//        List<MotelModel> books = motelService.getAllMotels();
//        model.addAttribute("userRooms", books);
//        return "motel";
//    }

//    @GetMapping("/edit/{title}")
//    public String getEditBookPage(Model model, @PathVariable String title) {
//        MotelModel byTitle = motelService.findByTitleAndDelete(title);
//        model.addAttribute("motelToEdit", byTitle);
//        return "motel_templates/edit_motel";
//    }

    @GetMapping("/rooms/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model) {
//        MotelModel room = MotelService.findById(id); // Replace with actual method to fetch the room
//        model.addAttribute("roomToEdit", room); // Bind the object to the Thymeleaf template
        return "motel_templates/edit_motel"; // Name of the Thymeleaf template (editRoom.html)
    }
}
