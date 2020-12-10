import { RouterModule, Routes } from '@angular/router';
import { OfferCreateComponent } from './offer-create/offer-create.component';

const routes: Routes = [
    {
        path: 'create',
        component: OfferCreateComponent
      }
];

export const OfferRoutingModule = RouterModule.forChild(routes);