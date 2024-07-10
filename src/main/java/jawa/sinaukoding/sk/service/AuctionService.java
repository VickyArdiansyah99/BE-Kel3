// package jawa.sinaukoding.sk.service;

// import jawa.sinaukoding.sk.entity.Auction;
// import jawa.sinaukoding.sk.entity.User;
// import jawa.sinaukoding.sk.model.Authentication;
// import jawa.sinaukoding.sk.model.Response;
// import jawa.sinaukoding.sk.model.request.SellerCreateAuctionReq;
// import jawa.sinaukoding.sk.repository.AuctionRepo;
// import org.springframework.core.env.Environment;
// import org.springframework.stereotype.Service;

// import java.math.BigInteger;
// import java.time.OffsetDateTime;
// import java.util.UUID;

// @Service
// public class AuctionService extends AbstractService {

//     private final AuctionRepo auctionRepo;

//     public AuctionService(final Environment env, final AuctionRepo auctionRepo) {
//         this.auctionRepo = auctionRepo;
//     }

//     private static String generateCode() {
//         return UUID.randomUUID().toString().substring(0, 8);
//     }

//     public Response<Object> createAuction(Authentication authentication, SellerCreateAuctionReq req) {
//         return precondition(authentication, User.Role.SELLER).orElseGet(() -> {
//             if (req.maximumPrice().compareTo(req.minimumPrice()) <= 0) {
//                 return Response.create("40", "03", "Harga maksimum harus lebih besar dari harga minimum.", null);
//             }

//             OffsetDateTime startedAt = OffsetDateTime.parse(req.startedAt());
//             OffsetDateTime endedAt = OffsetDateTime.parse(req.endedAt());
//             BigInteger offerPrice = req.maximumPrice().subtract(req.minimumPrice()).divide(BigInteger.TWO);

//             Auction auction = new Auction(
//                     generateCode(),
//                     req.name(),
//                     req.description(),
//                     offerPrice.intValue(), 
//                     0, 
//                     0L, 
//                     "", 
//                     Auction.Status.WAITING_FOR_APPROVAL,
//                     startedAt,
//                     endedAt,
//                     authentication.id()
//             );

//             Long savedAuctionId = auctionRepo.saveAuction(auction);

//             if (savedAuctionId == null) {
//                 return Response.create("05", "01", "Gagal membuat lelang.", null);
//             } else {
//                 return Response.create("05", "00", "Sukses membuat lelang.", savedAuctionId);
//             }
//         });
//     }
// }


package jawa.sinaukoding.sk.service;

import jawa.sinaukoding.sk.entity.Auction;
import jawa.sinaukoding.sk.entity.User;
import jawa.sinaukoding.sk.model.Authentication;
import jawa.sinaukoding.sk.model.Response;
import jawa.sinaukoding.sk.model.request.SellerCreateAuctionReq;
import jawa.sinaukoding.sk.repository.AuctionRepo;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public final class AuctionService extends AbstractService {

    private final AuctionRepo auctionRepo;

    public AuctionService(final Environment env, final AuctionRepo auctionRepo) {
        this.auctionRepo = auctionRepo;
    }

   
    public Response<Object> createAuction(final Authentication authentication, final SellerCreateAuctionReq req) {
        return precondition(authentication, User.Role.SELLER).orElseGet(() -> {
            if (req == null) {
                return Response.badRequest();
            }

            OffsetDateTime startedAt = OffsetDateTime.parse(req.startedAt());
            OffsetDateTime endedAt = OffsetDateTime.parse(req.endedAt());
            BigInteger offerPrice = req.maximumPrice().subtract(req.minimumPrice()).divide(BigInteger.TWO);


            Auction auction = new Auction(
                null, 
                UUID.randomUUID().toString().substring(0,8).toUpperCase(),
                req.name(), 
                req.description(),
                offerPrice, 
                offerPrice, 
                0L, 
                "", 
                Auction.Status.WAITING_FOR_APPROVAL, 
                startedAt, 
                endedAt, 
                authentication.id(), 
                null, 
                null, 
                OffsetDateTime.now(ZoneOffset.UTC), 
                null, 
                null);

            final Long saved = auctionRepo.saveAuction(auction);
            System.out.println(saved);
        

            if (saved == 0L) {
                return Response.create("05", "01", "gagal buat lelang.", null);
            } else {
                return Response.create("05", "00", "Sukses buat lelang.", saved);
            }
        });
    }
}
