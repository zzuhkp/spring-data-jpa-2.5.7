/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.jpa.repository.support;

import static org.assertj.core.api.Assertions.*;

import java.io.Serializable;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.test.context.ContextConfiguration;

/**
 * EclipseLink execution for {@link JpaMetamodelEntityInformationIntegrationTests}.
 *
 * @author Oliver Gierke
 * @author Jens Schauder
 */
@ContextConfiguration("classpath:eclipselink.xml")
class EclipseLinkJpaMetamodelEntityInformationIntegrationTests
		extends JpaMetamodelEntityInformationIntegrationTests {

	/**
	 * Re-activate test. Change to check for {@link String} as OpenJpa defaults {@link Serializable}s to {@link String}.
	 */
	@Test
	void reactivatedDetectsIdTypeForMappedSuperclass() {
		JpaEntityInformation<?, ?> information = JpaEntityInformationSupport.getEntityInformation(AbstractPersistable.class,
				em);
		assertThat(information.getIdType()).isEqualTo(String.class);
	}

	/**
	 * Ignored due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=411231.
	 */
	@Override
	@Disabled
	void findsIdClassOnMappedSuperclass() {}

	/**
	 * Ignored due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=415027
	 */
	@Override
	@Disabled
	void detectsNewStateForEntityWithPrimitiveId() {}

	@Override
	@Disabled
	void considersEntityWithUnsetCompundIdNew() {}

	/**
	 * Re-activate test for DATAJPA-820.
	 */
	@Test
	@Override
	void detectsVersionPropertyOnMappedSuperClass() {
		super.detectsVersionPropertyOnMappedSuperClass();
	}

	/**
	 * This test fails due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=531528 IdentifiableType.hasSingleIdAttribute()
	 * returns true when IdClass references an inner class. This bug is supposedly fixed, but the test still fails.
	 */
	@Disabled
	@Test
	@Override
	void correctlyDeterminesIdValueForNestedIdClassesWithNonPrimitiveNonManagedType() {
		super.correctlyDeterminesIdValueForNestedIdClassesWithNonPrimitiveNonManagedType();
	}

	/**
	 * This test fails due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=531528 IdentifiableType.hasSingleIdAttribute()
	 * returns true when IdClass references an inner class. This bug is supposedly fixed, but the test still fails.
	 */
	@Disabled
	@Test
	@Override
	void proxiedIdClassElement() {
		super.proxiedIdClassElement();
	}

	@Override
	String getMetadadataPersitenceUnitName() {
		return "metadata_el";
	}
}
