import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
    // {
    //     path: '',
    //     pathMatch: 'full',
    //     // redirectTo: '/auth/login'
    //     component: LoginComponent
    // },

    {
        path: 'register',
        component: RegisterComponent
    },

    {
        path: 'login',
        component: LoginComponent
    },

];

export const AuthRoutingModule = RouterModule.forChild(routes);