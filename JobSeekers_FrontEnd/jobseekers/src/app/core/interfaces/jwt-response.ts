import { IUser } from './user';

export interface IJwtResponse {
    accessToken: string;
    type: string;
    username: string;
    authorities: string[];
}
