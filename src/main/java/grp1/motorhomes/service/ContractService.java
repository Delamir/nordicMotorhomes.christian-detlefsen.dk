package grp1.motorhomes.service;

import grp1.motorhomes.model.Constants;
import grp1.motorhomes.model.Contract;
import grp1.motorhomes.repository.ContractRepo;
import grp1.motorhomes.repository.MotorhomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * @author Christian
 */
@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;

    @Autowired
    MotorhomeRepo motorhomeRepo;

    /**
     * @author Christian
     */
    public List<Contract> fetchAllContracts() {
        return contractRepo.fetchAllContracts();
    }

    /**
     * @author Joachim
     */
    public List<Contract> fetchAllClosedContracts() {
        return contractRepo.fetchAllClosedContracts();
    }

    /**
     * @author Christian
     */
    public void createContract(Contract contract) {
        contractRepo.createContract(contract);
    }

    /**
     * @author Sverri
     */
    public Contract findContract(int contractId) {
        return contractRepo.findContract(contractId);
    }

    /**
     * @param contract
     * @author Sverri
     */
    public void editContract(Contract contract) {
        contractRepo.editContract(contract);
    }

    /**
     * @author Sverri
     */
    public void deleteContract(int contractId) {
        contractRepo.deleteContract(contractId);
    }

    /**
     * @author Christian og Patrick
     */
    public double calculatePrice(Contract contract) {
        double priceExtra = 0;
        double priceMotorhome;
        double transferFee = 0;
        double fuelCost = 0;
        double excessKm = 0;
        int rentalDays = daysBetweenDates(contract.getFromDate(),contract.getToDate());

        //Calculate price based on season
        if (LocalDateTime.now().getMonth().getValue() >= Month.MAY.getValue() &&
                LocalDateTime.now().getMonth().getValue() <= Month.SEPTEMBER.getValue()) {

            priceMotorhome = contract.getMotorhome().getPrice() * rentalDays; //peak season price

        } else if (LocalDateTime.now().getMonth().getValue() >= Month.NOVEMBER.getValue() ||
                LocalDateTime.now().getMonth().getValue() <= Month.FEBRUARY.getValue()) {

            priceMotorhome = (contract.getMotorhome().getPrice() - (contract.getMotorhome().getPrice() * Constants.LOW_SEASON)) * rentalDays; //low season price

        } else {
            priceMotorhome = (contract.getMotorhome().getPrice() - (contract.getMotorhome().getPrice() * Constants.MID_SEASON)) * rentalDays; //mid season price
        }

        //Calculate the price of all extras
        for (int i = 0; i < contract.getExtras().size(); i++) {
            priceExtra += contract.getExtras().get(i).getPrice();
        }

        priceExtra = priceExtra * rentalDays;

        //Calculate potential transfer fee
        if (contract.isDelivered() || contract.isPickedUp()) {
            transferFee = contract.getTransferKm() * Constants.TRANSFER_COST_PER_KM;
        }

        //Calculate potential fuel cost
        if (contract.isUnderHalfFuelTank()) {
            fuelCost = Constants.FUEL_COST_UNDER_HALF_TANK;
        }

        //Calculate potential fee on excess kilometers driven
        if (contract.getExcessKm() > 0) {
            excessKm = contract.getExcessKm() * Constants.FEE_ON_EXCESS_KM;
        }
          return priceExtra + priceMotorhome + transferFee + fuelCost + excessKm;
    }

    /**
     * @author Christian og Patrick
     */
    public double cancellationFee(Contract contract) {
        double contractPrice = calculatePrice(contract);
        double cancellationFee;
        int rentalDays = daysBetweenDates(LocalDateTime.now(), contract.getFromDate());

        if (rentalDays >= Constants.CANCELLATION_50_DAYS) {

            cancellationFee = contractPrice * Constants.CANCELLATION_FEE_50;

        } else if (rentalDays >= Constants.CANCELLATION_15_DAYS) {

            cancellationFee = contractPrice * Constants.CANCELLATION_FEE_49_TO_15;

        } else if (rentalDays > Constants.CANCELLATION_SAME_DAY) {

            cancellationFee = contractPrice * Constants.CANCELLATION_FEE_LESS_15;

        } else if (rentalDays == Constants.CANCELLATION_SAME_DAY) {

            cancellationFee = contractPrice * Constants.CANCELLATION_FEE_ON_DAY;

        } else {
            cancellationFee = contractPrice;
        }

        if (cancellationFee < Constants.MINIMUM_CANCELLATION_FEE) {
            cancellationFee = Constants.MINIMUM_CANCELLATION_FEE;
        }
        return cancellationFee;
    }

    /**
     * @author Sverri
     */
    public void deliverContract(Contract contract) throws Exception {
        contractRepo.deliverContract(contract);
    }

    /**
     * @author Joachim
     */
    public void pickupContract(Contract contract) throws Exception {
        contract.setExcessKm(calculateExcessKm(findContract(contract.getContractId()), contract.getOdometer()));
        contractRepo.pickupContract(contract);
    }

    /**
     * @author Sverri and Christian
     */
    public int daysBetweenDates(LocalDateTime from, LocalDateTime  to) {
        return (int) Duration.between(from.toLocalDate().atStartOfDay(),
                to.toLocalDate().atStartOfDay()).toDays();
    }

    /**
     * @author Sverri
     */
    public int calculateExcessKm(Contract contract, int endOdometer) {
        int rentalDays = daysBetweenDates(contract.getFromDate(),contract.getToDate());
        int kmDriven = endOdometer - contract.getOdometer();
        int kmDrivenPerDay = kmDriven / rentalDays;
        if (kmDrivenPerDay < 400)
            return 0;
        return kmDrivenPerDay * rentalDays - 400 * rentalDays;
    }

    /**
     * @author Joachim
     */
    public void closeContract(Contract contract) {
        contractRepo.closeContract(contract);
    }
}
