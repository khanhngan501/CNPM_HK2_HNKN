package com.group08.onlineShop.controller;

import com.group08.onlineShop.dto.requestDTO.AddressRequest;
import com.group08.onlineShop.dto.responseDTO.ApiResponse;
import com.group08.onlineShop.exception.ResourceNotFoundException;
import com.group08.onlineShop.model.Address;
import com.group08.onlineShop.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000")
public class AddressController {
    private final AddressService addressService;
    @GetMapping("/get-all-address")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllAddress() {
        return ResponseEntity.ok(new ApiResponse(true,
                "Success", HttpStatus.OK.value(), addressService.getAllAddress()));
    }

    @GetMapping("/get-address")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllAddress(
            @RequestParam(name="addressID") Long addressID
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(new ApiResponse(true,
                "Success", HttpStatus.OK.value(), addressService.getAddressByID(addressID)));
    }

    @PostMapping("/post-address")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createAddress(
            @RequestBody Address addressRequest
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(new ApiResponse(true,
                "Success", HttpStatus.OK.value(), addressService.createAddress(addressRequest)));
    }

    @PutMapping("/update-address")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateAddress(
            @RequestParam(name = "addressID") Long addressID,
            @RequestBody AddressRequest addressRequest
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(new ApiResponse(true,
                "Success", HttpStatus.OK.value(), addressService.updateAddress(addressID, addressRequest)));
    }

    @DeleteMapping("/delete-address")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteAddress(
            @RequestParam(name = "addressID") Long addressID
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(new ApiResponse(true,
                "Success", HttpStatus.OK.value(), addressService.deleteAddress(addressID)));
    }
}
