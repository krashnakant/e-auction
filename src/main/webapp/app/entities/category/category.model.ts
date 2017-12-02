import { BaseEntity } from './../../shared';

export class Category implements BaseEntity {
    constructor(
        public id?: number,
        public categoryName?: string,
        public categoryImageContentType?: string,
        public categoryImage?: any,
        public subCategories?: BaseEntity[],
    ) {
    }
}
