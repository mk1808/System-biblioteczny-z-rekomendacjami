import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibBookDetailsComponent } from './lib-book-details.component';

describe('LibBookDetailsComponent', () => {
  let component: LibBookDetailsComponent;
  let fixture: ComponentFixture<LibBookDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibBookDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LibBookDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
