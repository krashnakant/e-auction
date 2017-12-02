/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EauctionTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SubCategoryDetailComponent } from '../../../../../../main/webapp/app/entities/sub-category/sub-category-detail.component';
import { SubCategoryService } from '../../../../../../main/webapp/app/entities/sub-category/sub-category.service';
import { SubCategory } from '../../../../../../main/webapp/app/entities/sub-category/sub-category.model';

describe('Component Tests', () => {

    describe('SubCategory Management Detail Component', () => {
        let comp: SubCategoryDetailComponent;
        let fixture: ComponentFixture<SubCategoryDetailComponent>;
        let service: SubCategoryService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EauctionTestModule],
                declarations: [SubCategoryDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SubCategoryService,
                    JhiEventManager
                ]
            }).overrideTemplate(SubCategoryDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubCategoryDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubCategoryService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SubCategory(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.subCategory).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
