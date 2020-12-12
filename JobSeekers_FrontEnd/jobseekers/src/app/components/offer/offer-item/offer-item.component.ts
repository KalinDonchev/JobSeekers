import { Component, Input, OnInit } from '@angular/core';
import { IOfferInfoList } from 'src/app/core/interfaces/offer-info-list';

@Component({
  selector: 'app-offer-item',
  templateUrl: './offer-item.component.html',
  styleUrls: ['./offer-item.component.css']
})
export class OfferItemComponent implements OnInit {

  @Input() offer: IOfferInfoList;

  constructor() { }

  ngOnInit(): void {
    console.log(this.offer);
  }

}
