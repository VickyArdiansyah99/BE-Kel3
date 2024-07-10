// package jawa.sinaukoding.sk.controller;

// import jawa.sinaukoding.sk.model.Authentication;
// import jawa.sinaukoding.sk.model.Response;
// import jawa.sinaukoding.sk.model.request.SellerCreateAuctionReq;
// import jawa.sinaukoding.sk.service.AuctionService;
// import jawa.sinaukoding.sk.util.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/secured/auction")
// public class AuctionController {

//     private final AuctionService auctionService;

//     public AuctionController(AuctionService auctionService){
//         this.auctionService = auctionService;
//     }

//     // seller bisa createAuction
//     @PostMapping("/create")
//     public Response<Object> createAuction(@RequestBody SellerCreateAuctionReq req) {
//         Authentication authentication = SecurityContextHolder.getAuthentication();
//         return auctionService.createAuction(authentication, req);
//     }

//     // admin, bisa approve
//     @PostMapping("/approve")
//     public Response<Object> approveAuction() {
//         return Response.badRequest();
//     }

//     // admin, bisa reject
//     @PostMapping("/reject")
//     public Response<Object> rejectAuction() {
//         return Response.badRequest();
//     }
// }



package jawa.sinaukoding.sk.controller;

import jawa.sinaukoding.sk.model.Authentication;
import jawa.sinaukoding.sk.model.Response;
import jawa.sinaukoding.sk.model.request.SellerCreateAuctionReq;
import jawa.sinaukoding.sk.service.AuctionService;
import jawa.sinaukoding.sk.util.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secured/auction")
public class AuctionController  {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/create")
    public Response<Object> createAuction(@RequestBody SellerCreateAuctionReq req) {
        Authentication authentication = SecurityContextHolder.getAuthentication();
        return auctionService.createAuction(authentication, req);
    }

}
