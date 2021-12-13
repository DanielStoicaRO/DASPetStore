package com.das.project.mapper;

import com.das.project.dto.AddDonation;
import com.das.project.dto.DonationInfo;
import com.das.project.model.Donation;
import com.das.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper {

    private final UserMapper userMapper;

    @Autowired
    public DonationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Donation mapToDonation(AddDonation addDonation, User user){
        Donation donation = new Donation();
        donation.setUser(user);
        donation.setProduct(addDonation.getProduct());
        donation.setDetails(addDonation.getDetails());
        return donation;
    }

    public AddDonation mapToDonationAddDto(Donation donation){
        AddDonation addDonation = new AddDonation();
        addDonation.setUserId(donation.getUser().getId());
        addDonation.setProduct(donation.getProduct());
        addDonation.setDetails(donation.getDetails());
        return addDonation;
    }

    public DonationInfo mapFromDonationToDonationInfo(Donation donation){
        DonationInfo donationInfo = new DonationInfo();
        donationInfo.setProduct(donation.getProduct());
        donationInfo.setDetails(donation.getDetails());
        return donationInfo;
    }

}
