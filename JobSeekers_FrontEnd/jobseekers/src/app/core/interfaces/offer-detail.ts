import { IBase } from './base';
import { IUserDetails } from './user-details';

export interface IOfferDetails {
    id: string;
    title: string;
    description: string;
    price: number;
    category: string;
    imageUrl: string;
    userDetailsViewModel: IUserDetails
}