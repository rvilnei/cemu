import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuiaDetailComponent } from './guia-detail.component';

describe('GuiaDetailComponent', () => {
  let component: GuiaDetailComponent;
  let fixture: ComponentFixture<GuiaDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuiaDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuiaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
