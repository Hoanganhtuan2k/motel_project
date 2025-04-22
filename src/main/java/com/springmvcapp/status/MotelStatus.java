package com.springmvcapp.status;

public enum MotelStatus {
  EMPTY("Trống"),
  OCCUPIED("Đã thuê");

  private String statusName;

  MotelStatus(String statusName) {
    this.statusName = statusName;
  }

  public String getStatusName() {
    return this.statusName;
  }
}
