import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibBookSearchElementComponent } from './lib-book-search-element.component';

describe('LibBookSearchElementComponent', () => {
  let component: LibBookSearchElementComponent;
  let fixture: ComponentFixture<LibBookSearchElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibBookSearchElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LibBookSearchElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
