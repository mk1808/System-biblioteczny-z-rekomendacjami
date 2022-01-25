import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibBookSearchComponent } from './lib-book-search.component';

describe('LibBookSearchComponent', () => {
  let component: LibBookSearchComponent;
  let fixture: ComponentFixture<LibBookSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibBookSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LibBookSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
