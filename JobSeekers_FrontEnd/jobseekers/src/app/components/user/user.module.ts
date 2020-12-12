import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetailsComponent } from './details/details.component';
import { UserRoutingModule } from './user-routing';



@NgModule({
  declarations: [DetailsComponent],
  imports: [
    CommonModule,
    UserRoutingModule
  ],
  exports: [DetailsComponent]
})
export class UserModule { }
