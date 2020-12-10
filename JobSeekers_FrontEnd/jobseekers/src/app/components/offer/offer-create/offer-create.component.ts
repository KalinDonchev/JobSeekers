import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { ICategoryInfo } from 'src/app/core/interfaces/category-info';
import { CategoryService } from 'src/app/core/services/category.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { OfferService } from 'src/app/core/services/offer.service';
import { Router } from '@angular/router';
import { ICreateOffer } from 'src/app/core/interfaces/create-offer';
import { TokenStorageService } from 'src/app/core/services/token-storage.service';

@Component({
  selector: 'app-offer-create',
  templateUrl: './offer-create.component.html',
  styleUrls: ['./offer-create.component.css']
})
export class OfferCreateComponent implements OnInit {

  form: FormGroup;
  categories$: Observable<ICategoryInfo[]>;
  offer: ICreateOffer;
  errorMessage = '';

  constructor(private formBuilder: FormBuilder,
     private categoryService: CategoryService,
      private offerService: OfferService,
       private router: Router,
       private tokenService: TokenStorageService) { }

  ngOnInit(): void {
    this.categories$ = this.categoryService.getAllCategories();
    

    this.form = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(20), Validators.maxLength(60)]],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0.1)]],
      file: ['', Validators.required],
      category: ['', Validators.required],
      fileSource: ['', Validators.required],
    })

  }

  submitHandler() {
    const data = this.form.value;

    this.offer = {
      title: data.title,
      description: data.description,
      price: data.price,
      image: data.file,
      category: data.category,
      user: this.tokenService.getUsername()
    }
    
    console.log(this.offer);
    this.offerService.createOffer(this.offer).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.errorMessage = err.error.message;;
      }
    });

  }

  uploadFileEvent(imgFile: any) {

    if (imgFile.target.files.length > 0) {
      const file = imgFile.target.files[0];
      this.form.get('file').setValue(file);
    }

  }

}
