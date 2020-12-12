import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { IOfferInfoList } from 'src/app/core/interfaces/offer-info-list';
import { AuthService } from 'src/app/core/services/auth.service';
import { OfferService } from 'src/app/core/services/offer.service';

@Component({
  selector: 'app-offer-list',
  templateUrl: './offer-list.component.html',
  styleUrls: ['./offer-list.component.css']
})
export class OfferListComponent implements OnInit {

  offers$: Observable<IOfferInfoList[]>;
  userOffers$: Observable<IOfferInfoList[]>;
  userFavOffers$: Observable<IOfferInfoList[]>;
  latestOffers$: Observable<IOfferInfoList[]>;


  constructor(private authService: AuthService,private offerService: OfferService,private router: Router) { }

  get isLogged(): boolean {
    return this.authService.isAuthenticated();
  }

  get userUsername(): string {
    return this.authService.getCurrentUserUsername();
  }


  ngOnInit(): void {
    this.offers$ = this.offerService.getAllOffers();
    this.userOffers$ = this.offerService.getAllOffersByCreator(this.userUsername);
    this.userFavOffers$ = this.offerService.getAllFavOffersByUser(this.userUsername);
    this.latestOffers$ = this.offerService.getLatestOffers(3);
   }

  isHomeRoute() {
    return this.router.url === '/home';
  }

  isMyOffersRoute() {
    return this.router.url.includes("/offer/my-offer");
  }

}
