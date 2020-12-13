import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { IOfferInfoList } from 'src/app/core/interfaces/offer-info-list';
import { AuthService } from 'src/app/core/services/auth.service';
import { OfferService } from 'src/app/core/services/offer.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  images = [
    {
        path: 'https://res.cloudinary.com/kalindonchev-cloud/image/upload/v1607854505/ir2ymv0iwcn7ww8fddvv.jpg',
    },
    {
        path: 'https://res.cloudinary.com/kalindonchev-cloud/image/upload/v1607854509/mjyidy43lcup4giqh3ke.jpg',
    },
    {
        path: 'https://res.cloudinary.com/kalindonchev-cloud/image/upload/v1607854514/xth2awefpgcrhq7t7eoj.jpg',
    }
  ]

  

  constructor(private authService: AuthService) { }

  get isLogged(): boolean {
    return this.authService.isAuthenticated();
  }

  get userUsername(): string {
    return this.authService.getCurrentUserUsername();
  }

  ngOnInit(): void {
   
  }




}
