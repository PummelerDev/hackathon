package com.hackathon.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.well-known/acme-challenge/EMuPGPAy71BWpvz622lXR-UGMmIiihbbmVvU67Zmthk")
public class SslController {

  @GetMapping()
  public String getImageList() {
    return "EMuPGPAy71BWpvz622lXR-UGMmIiihbbmVvU67Zmthk.BPjmvhJO2ADiTitXTDCnlUBVNMaP12PGR9YQRi-S66A";
  }
}
