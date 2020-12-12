import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavOffersComponent } from './fav-offers.component';

describe('FavOffersComponent', () => {
  let component: FavOffersComponent;
  let fixture: ComponentFixture<FavOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FavOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FavOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
