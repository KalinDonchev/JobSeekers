import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ICreateOffer } from 'src/app/core/interfaces/create-offer';
import { IOfferDetails } from 'src/app/core/interfaces/offer-detail';
import { AuthService } from 'src/app/core/services/auth.service';
import { OfferService } from 'src/app/core/services/offer.service';
import { UserService } from 'src/app/core/services/user.service';


@Component({
  selector: 'app-offer-detail',
  templateUrl: './offer-detail.component.html',
  styleUrls: ['./offer-detail.component.css']
})
export class OfferDetailComponent implements OnInit {

  offer: IOfferDetails;
  errorMessage = '';
  id;

  constructor(private offerService: OfferService, activatedRoute: ActivatedRoute, private authService: AuthService,private router: Router, private userService: UserService) {
    this.id = activatedRoute.snapshot.params.id;
    this.offerService.loadOffer(this.id).subscribe(offer => {
      this.offer = offer;
      console.log(this.offer)
    });
   }

  get isLogged(): boolean {
    return this.authService.isAuthenticated();
  }

  get currentUsername(): string {
    return this.authService.getCurrentUserUsername();
  }

  ngOnInit(): void {
    
  }

  deleteOffer(){

    const params = {
      offerId: this.id,
    }

    this.offerService.deleteOffer(params).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.errorMessage = err.error.message;;
      }
    });

  }

  addToFavourites(){

    const params = {
      offerId: this.id,
    }  

    this.userService.addToFavourites(params).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.errorMessage = err.error.message;;
      }
    });
    

  }


  isMyFavOffersRoute() {
    return this.router.url.includes("/offer/favourite-offers");
  }

}
