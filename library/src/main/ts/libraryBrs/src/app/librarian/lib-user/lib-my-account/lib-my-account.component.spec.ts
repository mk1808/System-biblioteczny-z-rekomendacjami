import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibMyAccountComponent } from './lib-my-account.component';

describe('LibMyAccountComponent', () => {
  let component: LibMyAccountComponent;
  let fixture: ComponentFixture<LibMyAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibMyAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LibMyAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
