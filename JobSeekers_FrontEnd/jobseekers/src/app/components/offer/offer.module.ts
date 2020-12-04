import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OfferItemComponent } from './offer-item/offer-item.component';
import { OfferListComponent } from './offer-list/offer-list.component';

@NgModule({
    declarations: [OfferItemComponent, OfferListComponent],
    imports: [
      CommonModule,
      RouterModule
    ],
    exports: [OfferItemComponent, OfferListComponent]
  })
  export class OfferModule { }
  