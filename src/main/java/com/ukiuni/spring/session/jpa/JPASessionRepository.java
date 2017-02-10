/*
MIT License

Copyright (c) [2017] [ukiuni]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */package com.ukiuni.spring.session.jpa;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSession;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import com.ukiuni.spring.session.jpa.entity.SessionEntity;
import com.ukiuni.spring.session.jpa.repository.SessionEntityRepository;

@Component
public class JPASessionRepository implements SessionRepository<ExpiringSession> {
	@Autowired
	private SessionEntityRepository sessionDataRepository;

	@Value("${spring.session-timeout}")
	private Integer maxInactiveIntervalInSeconds;

	public JPASessionRepository() {
	}

	public void setMaxInactiveIntervalInSeconds(Integer maxInactiveIntervalInSeconds) {
		this.maxInactiveIntervalInSeconds = maxInactiveIntervalInSeconds;
	}

	@Override
	public ExpiringSession createSession() {
		MapSession mapSession = new MapSession();
		if (maxInactiveIntervalInSeconds != null) {
			mapSession.setMaxInactiveIntervalInSeconds(maxInactiveIntervalInSeconds);
		}
		return mapSession;
	}

	@Override
	public void save(ExpiringSession session) {
		SessionEntity sessionData = new SessionEntity();
		BeanUtils.copyProperties(session, sessionData);
		Map<String, Object> attributes = new HashMap<String, Object>();
		for (String attrName : session.getAttributeNames()) {
			attributes.put(attrName, session.getAttribute(attrName));
		}
		sessionData.setData(MapSerializer.serialize(attributes));
		sessionDataRepository.save(sessionData);
	}

	@Override
	public ExpiringSession getSession(String id) {
		SessionEntity sessionData = sessionDataRepository.findOne(id);
		MapSession mapSession = new MapSession();
		mapSession.setId(sessionData.getId());
		mapSession.setLastAccessedTime(sessionData.getLastAccessedTime());
		mapSession.setCreationTime(sessionData.getCreationTime());

		Map<String, Object> attributes = MapSerializer.desirialize(sessionData.getData());

		for (String attribute : attributes.keySet()) {
			mapSession.setAttribute(attribute, attributes.get(attribute));
		}
		return mapSession;
	}

	@Override
	public void delete(String id) {
		sessionDataRepository.delete(id);
	}
}
