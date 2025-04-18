package com.springmvcapp.service;

import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.service.repo.MotelModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MotelService {

    @Autowired
    private MotelModelRepository motelModelRepository;

    private static List<MotelModel> motels;


    public List<MotelModel> getAllMotels() {
        return motelModelRepository.findAll();
    }

//    public static List<MotelModel> generateFakeMotels() {
//        return Arrays.asList(
//                new MotelModel(
//                        1L,
//                        "Phòng trọ cao cấp Quận 1",
//                        "Phòng trọ rộng rãi, sạch sẽ, gần trung tâm.",
//                        5000000.0,
//                        30.0,
//                        "123 Đường Lê Lai, Quận 1, TP.HCM",
//                        1L,
//                        1L,
//                        ("image1.jpg "image2.jpg"),
//                        LocalDateTime.now().minusDays(3),
//                        false,
//                        null,
//                        null
//                ),
//                new MotelModel(
//                        2L,
//                        "Phòng trọ sinh viên giá rẻ",
//                        "Phòng trọ dành cho sinh viên, giá cực rẻ.",
//                        2000000.0,
//                        20.0,
//                        "456 Đường Nguyễn Trãi, Quận 5, TP.HCM",
//                        2L,
//                        2L,
//                        Arrays.asList("image3.jpg", "image4.jpg"),
//                        LocalDateTime.now().minusDays(7),
//                        true,
//                        LocalDateTime.now().minusMonths(1),
//                        LocalDateTime.now().plusMonths(2)
//                ),
//                new MotelModel(
//                        3L,
//                        "Phòng trọ gần biển Đà Nẵng",
//                        "Phòng trọ view biển, thoáng mát, tiện nghi.",
//                        3000000.0,
//                        25.0,
//                        "789 Đường Võ Nguyên Giáp, Sơn Trà, Đà Nẵng",
//                        3L,
//                        3L,
//                        Arrays.asList("image5.jpg", "image6.jpg"),
//                        LocalDateTime.now().minusDays(15),
//                        false,
//                        null,
//                        null
//                )
//        );
//    }

//    public List<MotelModel> getAllMotels() {
//        return generateFakeMotels();
//    }

//    public MotelModel findByTitleAndDelete(String title) {
//        MotelModel motelModel = generateFakeMotels().stream()
//                .filter(it -> it.getTitle().equals(title))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new);
//        generateFakeMotels().remove(motelModel);
//        return motelModel;
//    }
//
//    public static MotelModel findById(Long id) {
//        // Iterate through the list to find the motel with the matching ID
//        return generateFakeMotels()
//                .stream()
//                .filter(motel -> motel.getId().equals(id))
//                .findFirst()
//                .orElse(null); // Return null if no match is found
//    }
}
