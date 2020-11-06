import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuiaDeleteComponent } from './guia-delete.component';

describe('GuiaDeleteComponent', () => {
  let component: GuiaDeleteComponent;
  let fixture: ComponentFixture<GuiaDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuiaDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuiaDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
