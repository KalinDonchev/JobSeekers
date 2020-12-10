import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-offer-list',
  templateUrl: './offer-list.component.html',
  styleUrls: ['./offer-list.component.css']
})
export class OfferListComponent implements OnInit {

  constructor(private authService: AuthService) { }

  get isLogged(): boolean {
    return this.authService.isAuthenticated();
  }


  ngOnInit(): void {
  }

}
