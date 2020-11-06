import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransportadoraEditComponent } from './transportadora-edit.component';

describe('TransportadoraEditComponent', () => {
  let component: TransportadoraEditComponent;
  let fixture: ComponentFixture<TransportadoraEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransportadoraEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransportadoraEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
