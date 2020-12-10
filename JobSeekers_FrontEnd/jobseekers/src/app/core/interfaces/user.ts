import { from } from 'rxjs';
import { IBase } from './base';

export interface IUser extends IBase {
    username: string;
    password: string;
    email: string;
    phoneNumber: string;
   
}