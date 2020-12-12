import { RouterModule, Routes } from '@angular/router';
import { FavOffersComponent } from './fav-offers/fav-offers.component';
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
    component: FavOffersComponent
  },
];

export const OfferRoutingModule = RouterModule.forChild(routes);