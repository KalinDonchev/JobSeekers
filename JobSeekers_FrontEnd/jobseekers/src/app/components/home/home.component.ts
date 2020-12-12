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
        path: 'https://res.cloudinary.com/kalindonchev-cloud/image/upload/v1606763464/kjojbprq4q1jhdtb20wc.jpg',
    },
    {
        path: 'https://res.cloudinary.com/kalindonchev-cloud/image/upload/v1607515821/f7aavnoyivslhdtb7v2z.jpg',
    },
    {
        path: 'https://res.cloudinary.com/kalindonchev-cloud/image/upload/v1607515821/f7aavnoyivslhdtb7v2z.jpg',
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
