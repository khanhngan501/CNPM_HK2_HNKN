package com.group08.onlineShop.service.impl;

import com.group08.onlineShop.dto.requestDTO.CustomerInfoRequest;
import com.group08.onlineShop.dto.responseDTO.ApiResponse;
import com.group08.onlineShop.dto.responseDTO.CustomerInfoResponse;
import com.group08.onlineShop.exception.BadRequestException;
import com.group08.onlineShop.exception.ResourceNotFoundException;
import com.group08.onlineShop.model.Account;
import com.group08.onlineShop.model.Address;
import com.group08.onlineShop.model.CustomerInfo;
import com.group08.onlineShop.repository.AccountRepo;
import com.group08.onlineShop.repository.AddressRepo;
import com.group08.onlineShop.repository.CustomerInfoRepo;
import com.group08.onlineShop.service.CustomerInfoService;
import com.group08.onlineShop.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerInfoServiceImpl implements CustomerInfoService {
    private final CustomerInfoRepo customerInfoRepo;
    private final AccountRepo accountRepo;
    private final AddressRepo addressRepo;
    private final SequenceGeneratorService sequenceGeneratorService;
    @Override
    public List<CustomerInfoResponse> getAllCustomerInfo() {
        List<CustomerInfo> customerInfos = customerInfoRepo.findAll();
        return addCustomerInfoResponse(customerInfos);
    }

    @Override
    public List<CustomerInfoResponse> getCustomerInfoByAccount(Long accountID) throws ResourceNotFoundException {
        Account account = accountRepo.findById(accountID).orElseThrow(()
        -> new ResourceNotFoundException("Account", "accountID", accountID));
        List<CustomerInfo> customerInfos = customerInfoRepo.findCustomerInfosByAccount(account);
        return addCustomerInfoResponse(customerInfos);
    }

    @Override
    public CustomerInfoResponse getCustomerInfoDefaultByAccount(Long accountID, Boolean isDefault) throws ResourceNotFoundException {
        Account account = accountRepo.findById(accountID).orElseThrow(()
                -> new ResourceNotFoundException("Account", "accountID", accountID));
        CustomerInfo customerInfo = customerInfoRepo.findCustomerInfoByAccountAndIsDefault(account, true)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerInfo", "accountID", accountID));
        return new CustomerInfoResponse(customerInfo.getId(), customerInfo.getAccount(),
                customerInfo.getPhoneNumber(), customerInfo.getAddress(), customerInfo.getCustomerName(),
                customerInfo.getIsDefault());
    }

    @Override
    public CustomerInfoResponse getCustomerInfoByID(Long customerInfoID) throws ResourceNotFoundException {
        CustomerInfo customerInfo = customerInfoRepo.findById(customerInfoID)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerInfo", "customerInfoID", customerInfoID));
        return new CustomerInfoResponse(customerInfo.getId(), customerInfo.getAccount(),
                customerInfo.getPhoneNumber(), customerInfo.getAddress(), customerInfo.getCustomerName(),
                customerInfo.getIsDefault());
    }

    @Override
    public CustomerInfoResponse createCustomerInfo(CustomerInfo customerInfoRequest) throws ResourceNotFoundException {
        Account account = accountRepo.findById(customerInfoRequest.getAccount().getId()).orElseThrow(()
                -> new ResourceNotFoundException("Account", "accountID", customerInfoRequest.getAccount()));
        Address address = addressRepo.findById(customerInfoRequest.getAddress().getId()).orElseThrow(()
                -> new ResourceNotFoundException("Address", "addressID", customerInfoRequest.getAddress()));
        Long customerInfo_id = Long.valueOf(sequenceGeneratorService.generateSequence(account.SEQUENCE_NAME));
        var customerInfo = CustomerInfo.builder()
                .id(customerInfo_id)
                .account(account)
                .phoneNumber(customerInfoRequest.getPhoneNumber())
                .address(address)
                .customerName(customerInfoRequest.getCustomerName())
                .isDefault(customerInfoRequest.getIsDefault()).build();
        customerInfoRepo.save(customerInfo);
        return new CustomerInfoResponse(customerInfo.getId(), customerInfo.getAccount(),
                customerInfo.getPhoneNumber(), customerInfo.getAddress(), customerInfo.getCustomerName(),
                customerInfo.getIsDefault());
    }

    @Override
    public CustomerInfoResponse updateCustomerInfo(Long customerInfoID, CustomerInfoRequest customerInfoRequest) throws ResourceNotFoundException {
        Account account = accountRepo.findById(customerInfoRequest.getAccount()).orElseThrow(()
                -> new ResourceNotFoundException("Account", "accountID", customerInfoRequest.getAccount()));
        Address address = addressRepo.findById(customerInfoRequest.getAddress()).orElseThrow(()
                -> new ResourceNotFoundException("Address", "addressID", customerInfoRequest.getAddress()));
        CustomerInfo customerInfo = customerInfoRepo.findById(customerInfoID)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerInfo", "customerInfoID", customerInfoID));
        if (customerInfo != null) {
            customerInfo.setAccount(account);
            customerInfo.setPhoneNumber(customerInfoRequest.getPhoneNumber());
            customerInfo.setAddress(address);
            customerInfo.setCustomerName(customerInfoRequest.getCustomerName());
            CustomerInfo updatedCustomerInfo = customerInfoRepo.save(customerInfo);
            return new CustomerInfoResponse(updatedCustomerInfo.getId(), updatedCustomerInfo.getAccount(),
                    updatedCustomerInfo.getPhoneNumber(), updatedCustomerInfo.getAddress(),
                    updatedCustomerInfo.getCustomerName(), updatedCustomerInfo.getIsDefault());
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Can not update customer info", HttpStatus.BAD_REQUEST.value());
        throw new BadRequestException(apiResponse);
    }

    @Override
    public ApiResponse deleteCustomerInfo(Long customerInfoID) throws ResourceNotFoundException {
        CustomerInfo customerInfo = customerInfoRepo.findById(customerInfoID)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerInfo", "customerInfoID", customerInfoID));
        if (customerInfo != null) {
            customerInfoRepo.deleteById(customerInfoID);
            return new ApiResponse(Boolean.TRUE, "Customer info deleted successfully");
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Can not delete customer", HttpStatus.BAD_REQUEST.value());
        throw new BadRequestException(apiResponse);
    }

    private List<CustomerInfoResponse> addCustomerInfoResponse(List<CustomerInfo> customerInfos){
        List<CustomerInfoResponse> customerInfoResponses = new ArrayList<>(customerInfos.size());
        for(CustomerInfo customerInfo : customerInfos) {
            customerInfoResponses.add(new CustomerInfoResponse(customerInfo.getId(), customerInfo.getAccount(),
                    customerInfo.getPhoneNumber(), customerInfo.getAddress(), customerInfo.getCustomerName(),
                    customerInfo.getIsDefault()));
        }
        return customerInfoResponses;
    }
}
