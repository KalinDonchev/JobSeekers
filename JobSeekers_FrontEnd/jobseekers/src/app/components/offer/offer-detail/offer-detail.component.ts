import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ICreateOffer } from 'src/app/core/interfaces/create-offer';
import { IOfferDetails } from 'src/app/core/interfaces/offer-detail';
import { AuthService } from 'src/app/core/services/auth.service';
import { OfferService } from 'src/app/core/services/offer.service';


@Component({
  selector: 'app-offer-detail',
  templateUrl: './offer-detail.component.html',
  styleUrls: ['./offer-detail.component.css']
})
export class OfferDetailComponent implements OnInit {

  offer: IOfferDetails;
  errorMessage = '';
  id;

  constructor(private offerService: OfferService, activatedRoute: ActivatedRoute, private authService: AuthService,private router: Router) {
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
      username: this.currentUsername
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

}
