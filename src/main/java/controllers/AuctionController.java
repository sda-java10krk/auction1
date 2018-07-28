package controllers;

import DataBases.AuctionsDatabase;
import exceptions.auctionExceptions.AuctionAlreadyInDatabaseException;
import exceptions.auctionExceptions.AuctionsNotFoundException;
import exceptions.auctionExceptions.CannotAddInactiveAuctionToDatabaseException;
import models.Auction;
import models.Category;
import models.Offer;
import models.User;

import java.util.List;

public class AuctionController {

    public static void addAuction(Auction auction, User user, Category category) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        AuctionsDatabase.getInstance().addAuctionToDatabase(user, category, auction);
    }


    public static void addAuctionWon(Auction auction, String winner) throws AuctionAlreadyInDatabaseException {
        AuctionsDatabase.getInstance().addAuctionWon(winner, auction);
    }

    public static void addNewOffer(Auction auction, Offer offer) {

    }

    public static List<Auction> getAuctionsWon(String winnerLogin) throws AuctionsNotFoundException {
        List<Auction> auctions = AuctionsDatabase.getInstance().getWonAuctions(winnerLogin);
        if (auctions.isEmpty()) {
            throw new AuctionsNotFoundException();
        }
        return auctions;
    }

    public static List<Auction> getAuctionsByLogin(String login) throws AuctionsNotFoundException {
        List<Auction> auctions = AuctionsDatabase.getInstance().getAuctionsByLogin(login);
        if (auctions.isEmpty()) {
            throw new AuctionsNotFoundException();
        }
        return auctions;
    }
}
