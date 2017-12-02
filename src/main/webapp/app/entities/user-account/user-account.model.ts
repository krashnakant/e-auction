import { BaseEntity } from './../../shared';

export const enum UserAccountType {
    'SELLER',
    'BUYER'
}

export class UserAccount implements BaseEntity {
    constructor(
        public id?: number,
        public accountType?: UserAccountType,
        public userId?: number,
        public sales?: BaseEntity[],
    ) {
    }
}
