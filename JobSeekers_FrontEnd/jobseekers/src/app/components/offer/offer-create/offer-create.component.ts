import { Component, ElementRef, NgZone, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { ICategoryInfo } from 'src/app/core/interfaces/category-info';
import { CategoryService } from 'src/app/core/services/category.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { OfferService } from 'src/app/core/services/offer.service';
import { Router } from '@angular/router';
import { ICreateOffer } from 'src/app/core/interfaces/create-offer';
import { TokenStorageService } from 'src/app/core/services/token-storage.service';
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-offer-create',
  templateUrl: './offer-create.component.html',
  styleUrls: ['./offer-create.component.css']
})
export class OfferCreateComponent implements OnInit {

  @ViewChild('autosize') autosize: CdkTextareaAutosize;
  images = [];
  form: FormGroup;
  categories$: Observable<ICategoryInfo[]>;
  offer: ICreateOffer;
  errorMessage = '';

  constructor(private formBuilder: FormBuilder,
     private categoryService: CategoryService,
      private offerService: OfferService,
       private router: Router,
       private tokenService: TokenStorageService, private _ngZone: NgZone) { }

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
       
    this.offer = {
      title: this.f.title.value,
      description: this.f.description.value,
      price: this.f.price.value,
      images: this.f.fileSource.value,
      category: this.f.category.value,
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

  get f() {
    return this.form.controls;
  }

  uploadFileEvent(event) {

    if (event.target.files && event.target.files[0]) {

      var filesAmount = event.target.files.length;

      for (let i = 0; i < filesAmount; i++) {

        var reader = new FileReader();

        reader.onload = (event: any) => {

          this.images.push(event.target.result);

          this.form.patchValue({
            fileSource: this.images
          });
        }

        reader.readAsDataURL(event.target.files[i]);
      }
    }

  }

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1))
        .subscribe(() => this.autosize.resizeToFitContent(true));
  }

}
