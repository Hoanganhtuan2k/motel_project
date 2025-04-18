package com.springmvcapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationModel {
    private Long id;
    private String name; // Tên khu vực (VD: "Quận Hoàn Kiếm")
    private Double latitude; // Kinh độ
    private Double longitude; // Vĩ độ
    private Double radius; // Bán kính tìm kiếm
}
