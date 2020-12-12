import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OfferItemComponent } from './offer-item/offer-item.component';
import { OfferListComponent } from './offer-list/offer-list.component';
import { OfferCreateComponent } from './offer-create/offer-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OfferRoutingModule } from './offer-routing';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';

import { MatButtonModule } from '@angular/material/button';

import { OfferDetailComponent } from './offer-detail/offer-detail.component';
import { MyOffersComponent } from './my-offers/my-offers.component';

@NgModule({
  declarations: [OfferItemComponent, OfferListComponent, OfferCreateComponent, OfferDetailComponent, MyOffersComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    OfferRoutingModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    MatToolbarModule,

    MatButtonModule
  ],
  exports: [OfferItemComponent, OfferListComponent, OfferCreateComponent]
})
export class OfferModule { }
