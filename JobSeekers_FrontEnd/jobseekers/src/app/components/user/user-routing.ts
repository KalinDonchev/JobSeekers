import { RouterModule, Routes } from '@angular/router';
import { DetailsComponent } from './details/details.component';

const routes: Routes = [
    {
        path: 'profile/:username',
        component: DetailsComponent
    },

];

export const UserRoutingModule = RouterModule.forChild(routes);