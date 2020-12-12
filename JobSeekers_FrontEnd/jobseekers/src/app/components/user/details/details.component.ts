import { Component, OnInit } from '@angular/core';
import { IUserDetails } from 'src/app/core/interfaces/user-details';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  user: IUserDetails;

  constructor(private userService: UserService) {
    this.userService.getUserDetails().subscribe(user => {
      this.user = user;
      console.log(this.user)
    });
   }

  ngOnInit(): void {
  }

}
