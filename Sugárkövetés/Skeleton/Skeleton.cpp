//=============================================================================================
// Computer Graphics Sample Program: Ray-tracing-let
//
// A beadott program csak ebben a fajlban lehet, a fajl 1 byte-os ASCII karaktereket tartalmazhat, BOM kihuzando.
// Tilos:
// - mast "beincludolni", illetve mas konyvtarat hasznalni
// - faljmuveleteket vegezni a printf-et kiveve
// - Mashonnan atvett programresszleteket forrasmegjeloles nelkul felhasznalni es
// - felesleges programsorokat a beadott programban hagyni!!!!!!! 
// - felesleges kommenteket a beadott programba irni a forrasmegjelolest kommentjeit kiveve
// ---------------------------------------------------------------------------------------------
// A feladatot ANSI C++ nyelvu forditoprogrammal ellenorizzuk, a Visual Studio-hoz kepesti elteresekrol
// es a leggyakoribb hibakrol (pl. ideiglenes objektumot nem lehet referencia tipusnak ertekul adni)
// a hazibeado portal ad egy osszefoglalot.
// ---------------------------------------------------------------------------------------------
// A feladatmegoldasokban csak olyan OpenGL fuggvenyek hasznalhatok, amelyek az oran a feladatkiadasig elhangzottak 
// A keretben nem szereplo GLUT fuggvenyek tiltottak.
//
// NYILATKOZAT
// ---------------------------------------------------------------------------------------------
// Nev    : Szarvady Ambrus
// Neptun : QSOZTF
// ---------------------------------------------------------------------------------------------
// ezennel kijelentem, hogy a feladatot magam keszitettem, es ha barmilyen segitseget igenybe vettem vagy
// mas szellemi termeket felhasznaltam, akkor a forrast es az atvett reszt kommentekben egyertelmuen jeloltem.
// A forrasmegjeloles kotelme vonatkozik az eloadas foliakat es a targy oktatoi, illetve a
// grafhazi doktor tanacsait kiveve barmilyen csatornan (szoban, irasban, Interneten, stb.) erkezo minden egyeb
// informaciora (keplet, program, algoritmus, stb.). Kijelentem, hogy a forrasmegjelolessel atvett reszeket is ertem,
// azok helyessegere matematikai bizonyitast tudok adni. Tisztaban vagyok azzal, hogy az atvett reszek nem szamitanak
// a sajat kontribucioba, igy a feladat elfogadasarol a tobbi resz mennyisege es minosege alapjan szuletik dontes.
// Tudomasul veszem, hogy a forrasmegjeloles kotelmenek megsertese eseten a hazifeladatra adhato pontokat
// negativ elojellel szamoljak el es ezzel parhuzamosan eljaras is indul velem szemben.
//=============================================================================================
#include "framework.h"

const float epsilon = 0.0001f;

enum MaterialType { ROUGH, REFLECTIVE };
struct Material {
	vec3 ka, kd, ks;
	float  shininess;
	vec3 F0;
	MaterialType type;
	Material(MaterialType t) { type = t; }
};
struct RoughMaterial : Material {
	RoughMaterial(vec3 _kd, vec3 _ks, float _shininess) :Material(ROUGH) {
		ka = _kd * M_PI;
		kd = _kd;
		ks = _ks;
		shininess = _shininess;
	}
};

vec3 operator/(vec3 a, vec3 b) {
	return vec3(a.x / b.x, a.y / b.y, a.z / b.z);
}
struct ReflectiveMaterial : Material {
	ReflectiveMaterial(vec3 n, vec3 kappa) : Material(REFLECTIVE) {
		F0 = ((n - vec3(1, 1, 1)) * (n - vec3(1, 1, 1)) + kappa * kappa) / ((n + vec3(1, 1, 1)) * (n + vec3(1, 1, 1)) + kappa * kappa);
	}
};

struct Hit {
	float t;
	vec3 position, normal;
	Material* material;
	Hit() { t = -1; rotate = false; }
	bool rotate;
};

struct Ray {
	vec3 start, dir;
	Ray(vec3 _start, vec3 _dir) { start = _start; dir = normalize(_dir); }
};

class Object {
protected:
	Material* material;
public:
	virtual Hit intersect(const Ray& ray) = 0;
};

class Dodekaeder {
protected:
	vec3 vertices[20];
	int pagesIndex[60] = { 1, 2, 16, 5, 13,
			1, 13, 9, 10, 14,
			1, 14, 6, 15, 2,
			2, 15, 11, 12, 16,
			3, 4, 18, 8, 17,
			3, 17, 12, 11, 20,
			3, 20, 7, 19, 4,
			19, 10, 9, 18, 4,
			16, 12, 17, 8, 5,
			5, 8, 18, 9, 13,
			14, 10, 19, 7, 6,
			6, 7, 20, 11, 15 };
public:
	Dodekaeder() {
		vertices[0] = vec3(0.0f, 0.618034f, 1.61803f);
		vertices[1] = vec3(0.0f, -0.618034f, 1.61803f);
		vertices[2] = vec3(0.0f, -0.618034f, -1.61803f);
		vertices[3] = vec3(0.0f, 0.618034f, -1.61803f);

		vertices[4] = vec3(1.61803f, 0.0f, 0.618034f);
		vertices[5] = vec3(-1.61803f, 0.0f, 0.618034f);
		vertices[6] = vec3(-1.61803f, 0.0f, -0.618034f);
		vertices[7] = vec3(1.61803, 0, -0.618034);

		vertices[8] = vec3(0.618034f, 1.61803f, 0.0f);
		vertices[9] = vec3(-0.618034f, 1.61803f, 0.0f);
		vertices[10] = vec3(-0.618034f, -1.61803f, 0.0f);
		vertices[11] = vec3(0.618034f, -1.61803f, 0.0f);

		vertices[12] = vec3(1.0f, 1.0f, 1.0f);
		vertices[13] = vec3(-1.0f, 1.0f, 1.0f);
		vertices[14] = vec3(-1.0f, -1.0f, 1.0f);
		vertices[15] = vec3(1.0f, -1.0f, 1.0f);

		vertices[16] = vec3(1.0f, -1.0f, -1.0f);
		vertices[17] = vec3(1.0f, 1.0f, -1.0f);
		vertices[18] = vec3(-1.0f, 1.0f, -1.0f);
		vertices[19] = vec3(-1.0f, -1.0f, -1.0f);

	}

};

class Gold : public Object {
	mat4 Q;
public:
	Gold(mat4& _Q, Material* _material) {
		Q = _Q;
		material = _material;
	}
	vec3 gradf(vec3 r) {
		vec4 g = vec4(r.x, r.y, r.z, 1) * Q * 2;
		return vec3(g.x, g.y, g.z);
	}
	Hit intersect(const Ray& r) {
		Hit hit;
		vec4 S(r.start.x, r.start.y, r.start.z, 1);
		vec4 D(r.dir.x, r.dir.y, r.dir.z, 0);
		float a = dot(D * Q, D), b = dot(S * Q, D) * 2, c = dot(S * Q, S);
		float discr = b * b - 4.0f * a * c;
		if (discr < 0)
			return hit;

		float sqrt_discr = sqrtf(discr);
		float t1 = (-b + sqrt_discr) / 2.0f / a;
		vec3 p1 = r.start + r.dir * t1;
		if (dot(p1, p1) > 0.3f * 0.3f)
			t1 = -1;
		float t2 = (-b - sqrt_discr) / 2.0f / a;
		vec3 p2 = r.start + r.dir * t2;
		if (dot(p2, p2) > 0.3f * 0.3f)
			t2 = -1;
		if (t1 <= 0 && t2 <= 0)
			return hit;
		if (t1 <= 0)
			hit.t = t2;
		else if (t2 <= 0)
			hit.t = t1;
		else if (t2 < t1)
			hit.t = t2;
		else
			hit.t = t1;
		hit.position = r.start + r.dir * hit.t;
		hit.normal = normalize(gradf(hit.position));
		hit.position = hit.position;
		hit.material = material;
		return hit;
	}

};

vec4 qmul(vec4 q1, vec4 q2) {
	vec3 d1(q1.x, q1.y, q1.z), d2(q2.x, q2.y, q2.z);
	vec3 tmp = vec3(d2 * q1.w + d1 * q2.w + cross(d1, d2));
	return vec4(tmp.x, tmp.y, tmp.z, q1.w * q2.w - dot(d1, d2));
}
vec4 quaternion(float ang, vec3 axis) {
	vec3 d = normalize(axis) * sinf(ang / 2);
	return vec4(d.x, d.y, d.z, cosf(ang / 2));
}
vec3 Rotate(vec3 u, vec4 q) {
	vec4 qinv(-q.x, -q.y, -q.z, q.w);
	vec4 qr = qmul(qmul(q, vec4(u.x, u.y, u.z, 0)), qinv);
	return vec3(qr.x, qr.y, qr.z);
}

class Mirror : public Object, public Dodekaeder {
	vec3 pentagons[60];
public:
	Mirror(Material* _material) {
		material = _material;
		for (int i = 0; i < 60; i++)
		{
			pentagons[i] = vertices[pagesIndex[i] - 1];
		}
	}
	void getObjPlane(int i, vec3& p, vec3& normal)
	{
		vec3 p1 = pentagons[5 * i];
		vec3 p2 = pentagons[5 * i + 1];
		vec3 p3 = pentagons[5 * i + 2];
		normal = cross(p2 - p1, p3 - p1);
		if (dot(p1, normal) < 0)
			normal = -normal;
		p = p1;


	}
	Hit intersect(const Ray& r) {
		bool isItInside;
		Hit best = Hit();
		vec3 p11, n;

		for (int i = 0; i < 12; i++)
		{
			vec3 p1, normal;
			getObjPlane(i, p1, normal);
			float ti = fabs(dot(normal, r.dir)) > epsilon ? dot(p1 - r.start, normal) / dot(normal, r.dir) : -1;
			if (ti <= epsilon || (ti > best.t && best.t > 0))
			{
				continue;
			}
			vec3 pintersect = r.start + r.dir * ti;

			isItInside = true;
			for (int j = 0; j < 12; j++)
			{
				if (i == j)
					continue;

				getObjPlane(j, p11, n);

				if (dot(n, pintersect - p11) > 0) {
					isItInside = false;
					break;
				}
			}

			if (isItInside)
			{
				best.t = ti;
				best.position = pintersect;
				best.normal = normalize(normal);
				best.material = material;

				bool reflective = true;

				for (int k = i * 5; k < 5 * i + 5; k++)
				{
					int tmp = k + 1;
					if (tmp == 5 * i + 5)
						tmp = 5 * i;


					float dist = length(cross(pintersect - pentagons[k], pentagons[tmp] - pentagons[k])) / length(pentagons[tmp] - pentagons[k]);
					if (dist < 0.1f)
					{

						vec3 kd(0.15f, 0.1f, 0.1f), ks(0.2f, 0.2f ,0.2f);
						Material* mate = new RoughMaterial(kd, ks, 10);
						best.material = mate;
						reflective = false;

					}

				}
				if (reflective)
					best.rotate = true;

			}

		}

		return best;
	}

};


class Camera {
	vec3 eye, lookat, right, up;
	float Fov;
public:
	void set(vec3 _eye, vec3 _lookat, vec3 vup, float fov) {
		eye = _eye;
		lookat = _lookat;
		Fov = fov;
		vec3 w = eye - lookat;
		float focus = length(w);
		right = normalize(cross(vup, w)) * focus * tanf(fov / 2);
		up = normalize(cross(w, right)) * focus * tanf(fov / 2);
	}
	Ray getRay(int X, int Y) {
		vec3 dir = lookat + right * (2.0f * (X + 0.5f) / windowWidth - 1) + up * (2.0f * (Y + 0.5f) / windowHeight - 1) - eye;
		return Ray(eye, dir);
	}
	void Animate(float dt) {
		vec3 d = eye - lookat;
		eye = vec3(d.x * cos(dt) + d.z * sin(dt), d.y, -d.x * sin(dt) + d.z * cos(dt)) + lookat;
		set(eye, lookat, up, Fov);
	}
};

struct Light {
	vec3 position;
	vec3 Le;
	Light(vec3 _Le, vec3 _position) {
		Le = _Le;
		position = _position;
	}
};

float rnd() { return (float)rand() / RAND_MAX; }


class Scene {
	std::vector<Object*> objects;
	std::vector<Light*> lights;
	Camera camera;
	vec3 La;
public:
	void build() {
		vec3 eye = vec3(0, 0.3f, 1), vup = vec3(0, 1, 0), lookat = vec3(0, 0, 0);
		float fov = 45 * M_PI / 180;
		camera.set(eye, lookat, vup, fov);

		La = vec3(0.5f, 0.5f, 0.5f);

		vec3 Le(2,2,2), LightPosition(0, 0, 0);
		lights.push_back(new Light(Le, LightPosition));


		vec3 n(1,1,1), kappa(7, 15, 15);
		Material* material = new ReflectiveMaterial(n, kappa);
		objects.push_back(new Mirror(material));

		mat4 gold = mat4(16.3f, 0, 0, 0,
			0, 32.3f, 0, 0,
			0, 0, 0, 1.1f,
			0, 0, 1.1f, 0);
		objects.push_back(new Gold(gold, new ReflectiveMaterial(vec3(0.17f, 0.35f, 1.5f), vec3(3.1f, 2.7f, 1.9f))));

	}

	void render(std::vector<vec4>& image) {
		for (int Y = 0; Y < windowHeight; Y++) {
			for (int X = 0; X < windowWidth; X++) {
				vec3 color = trace(camera.getRay(X, Y));
				image[Y * windowWidth + X] = vec4(color.x, color.y, color.z, 1);
			}
		}
	}

	Hit firstIntersect(Ray ray) {
		Hit bestHit;
		for (Object* object : objects) {
			Hit hit = object->intersect(ray);

			if (hit.t > 0 && (bestHit.t < 0 || bestHit.t > hit.t))
			{
				bestHit = hit;
			}
		}
		if (dot(ray.dir, bestHit.normal) > 0) bestHit.normal = bestHit.normal * (-1);
		return bestHit;
	}

	vec3 trace(Ray ray, int depth = 0) {
		if (depth > 5)
			return La;
		Hit hit = firstIntersect(ray);
		if (hit.t < 0) return La;
		vec3 outRadiance = vec3(0, 0, 0);

		if (hit.material->type == ROUGH)
		{
			outRadiance = hit.material->ka * La;
			vec3 shadedPoint = hit.position + hit.normal * epsilon;
			for (Light* light : lights) {
				vec3 hitPosToLight = normalize(light->position - shadedPoint);
				float cosTheta = dot(hit.normal, hitPosToLight);
				float degrade = 1 / (length(hitPosToLight) * length(hitPosToLight));

				if (cosTheta > 0) {
					outRadiance = outRadiance + light->Le * hit.material->kd * degrade * cosTheta;
					vec3 halfway = normalize(-ray.dir + normalize(hitPosToLight));
					float cosDelta = dot(hit.normal, halfway);
					if (cosDelta > 0) outRadiance = outRadiance + light->Le * degrade * hit.material->ks * powf(cosDelta, hit.material->shininess);
				}
			}

		}

		else if (hit.material->type == REFLECTIVE)
		{
			vec3 reflectionDir = ray.dir - hit.normal * dot(hit.normal, ray.dir) * 2.0f;
			float cosIn = -dot(ray.dir, hit.normal);
			vec3 F = hit.material->F0 + (vec3(1, 1, 1) - hit.material->F0) * pow(1 - cosIn, 5);


			if (hit.rotate)
			{
				vec4 rotate = quaternion(72, hit.normal);
				reflectionDir = Rotate(reflectionDir, rotate);
				hit.position = Rotate(hit.position, rotate);
			}
			Ray spinR = Ray(hit.position + hit.normal * epsilon, reflectionDir);
			outRadiance = outRadiance + trace(spinR, depth + 1) * F;
		}
		
		return outRadiance;
	}
	void Animate(float dt) {
		camera.Animate(dt);

	}
};

GPUProgram gpuProgram; 
Scene scene;

const char* vertexSource = R"(
	#version 330
    precision highp float;

	layout(location = 0) in vec2 cVertexPosition;	// Attrib Array 0
	out vec2 texcoord;

	void main() {
		texcoord = (cVertexPosition + vec2(1, 1))/2;							// -1,1 to 0,1
		gl_Position = vec4(cVertexPosition.x, cVertexPosition.y, 0, 1); 		// transform to clipping space
	}
)";

const char* fragmentSource = R"(
	#version 330
    precision highp float;

	uniform sampler2D textureUnit;
	in  vec2 texcoord;			// interpolated texture coordinates
	out vec4 fragmentColor;		// output that goes to the raster memory as told by glBindFragDataLocation

	void main() {
		fragmentColor = texture(textureUnit, texcoord); 
	}
)";

class FullScreenTexturedQuad {
	unsigned int vao;	
	Texture texture;
public:
	FullScreenTexturedQuad(int windowWidth, int windowHeight, std::vector<vec4>& image)
		: texture(windowWidth, windowHeight, image)
	{
		glGenVertexArrays(1, &vao);	
		glBindVertexArray(vao);		

		unsigned int vbo;		
		glGenBuffers(1, &vbo);	

		glBindBuffer(GL_ARRAY_BUFFER, vbo); 
		float vertexCoords[] = { -1, -1,  1, -1,  1, 1,  -1, 1 };	
		glBufferData(GL_ARRAY_BUFFER, sizeof(vertexCoords), vertexCoords, GL_STATIC_DRAW);	  
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, 0, NULL);    

	}

	void Draw() {

		glBindVertexArray(vao);	
		gpuProgram.setUniform(texture, "textureUnit");

		glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
	}
	void LoadTexture(std::vector<vec4> i) {
		glBindTexture(GL_TEXTURE_2D, texture.textureId);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, windowWidth, windowHeight, 0, GL_RGBA, GL_FLOAT, &i[0]);
	}
};

FullScreenTexturedQuad* fullScreenTexturedQuad;

void onInitialization() {
	glViewport(0, 0, windowWidth, windowHeight);
	scene.build();

	std::vector<vec4> image(windowWidth * windowHeight);
	long timeStart = glutGet(GLUT_ELAPSED_TIME);
	scene.render(image);
	long timeEnd = glutGet(GLUT_ELAPSED_TIME);
	printf("Rendering time: %d milliseconds\n", (timeEnd - timeStart));

	fullScreenTexturedQuad = new FullScreenTexturedQuad(windowWidth, windowHeight, image);

	gpuProgram.create(vertexSource, fragmentSource, "fragmentColor");
}

void onDisplay() {
	std::vector<vec4> image(windowWidth * windowHeight);
	scene.render(image);
	fullScreenTexturedQuad->LoadTexture(image);
	fullScreenTexturedQuad->Draw();
	glutSwapBuffers();								
}

void onKeyboard(unsigned char key, int pX, int pY) {
}

void onKeyboardUp(unsigned char key, int pX, int pY) {

}

void onMouse(int button, int state, int pX, int pY) {
}

void onMouseMotion(int pX, int pY) {
}

void onIdle() {
	scene.Animate(0.1f);
	glutPostRedisplay();
}