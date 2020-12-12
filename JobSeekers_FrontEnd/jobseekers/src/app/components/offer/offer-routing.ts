import { RouterModule, Routes } from '@angular/router';
import { MyOffersComponent } from './my-offers/my-offers.component';
import { OfferCreateComponent } from './offer-create/offer-create.component';
import { OfferDetailComponent } from './offer-detail/offer-detail.component';

const routes: Routes = [
  {
    path: 'create',
    component: OfferCreateComponent
  },
  {
    path: 'details/:id',
    component: OfferDetailComponent
  },
  {
    path: 'my-offers/:username',
    component: MyOffersComponent
  },
  {
    path: 'favourite-offers/:username',
    component: MyOffersComponent
  },
];

export const OfferRoutingModule = RouterModule.forChild(routes);